<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<stripes:useActionBean beanclass="com.oreilly.hh.web.AlbumActionBean" var="actionBean" event="edit" />
<h1>Add a comment for the album <span style="font-style: italic">${actionBean.album.title }</span></h1>
<stripes:form action="/Album.action" >
    <stripes:hidden name="album.id" />
    Comment: <stripes:text name="comment" />
    <br />
    <stripes:submit name="saveComment" value="Save" />
</stripes:form>