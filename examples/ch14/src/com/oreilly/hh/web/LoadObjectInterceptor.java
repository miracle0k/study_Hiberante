package com.oreilly.hh.web;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.*;
import net.sourceforge.stripes.integration.spring.*;
import net.sourceforge.stripes.util.bean.BeanUtil;

@Intercepts({LifecycleStage.BindingAndValidation})
public class LoadObjectInterceptor extends SpringInterceptorSupport 
    implements Interceptor {

    HibernateTemplate hibernateTemplate;
    private Logger log = Logger.getLogger(LoadObjectInterceptor.class);

    public Resolution intercept(ExecutionContext ctx) throws Exception {
        Method handler = ctx.getHandler();
        LoadBean loadProperty = handler.getAnnotation(LoadBean.class);
        if(loadProperty != null && loadProperty.value() != "" 
                && loadProperty.value() != null ) {
            String propertyName = loadProperty.value();
            String idName = propertyName+".id";
            HttpServletRequest request = ctx.getActionBeanContext().getRequest();
            String idValue = request.getParameter(idName);
            Class<?> propertyClass = 
                BeanUtil.getPropertyType(propertyName, ctx.getActionBean());
            
            if(idValue != null && idValue != "" ) {
                Object o = hibernateTemplate.get(propertyClass, 
                                                 Integer.valueOf(idValue));
            
                BeanUtil.setPropertyValue(propertyName, 
                                          ctx.getActionBean(), 
                                          propertyClass.cast(o));
            }
        }
        Resolution resolution = ctx.proceed();
        return resolution;
    }
    
    @SpringBean("hibernateTemplate")
    public void injectHibernateTemplate(HibernateTemplate aHibernateTemplate) {
        this.hibernateTemplate = aHibernateTemplate;
    }
}
