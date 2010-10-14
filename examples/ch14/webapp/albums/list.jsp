<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stripes"
  uri="http://stripes.sourceforge.net/stripes.tld"%>
<stripes:useActionBean beanclass="com.oreilly.hh.web.AlbumActionBean"
  var="actionBean" event="list" />
<table>
  <tr>
    <th>title</th>
    <th>discs</th>
    <th>action</th>
  </tr>
  <c:forEach items="${actionBean.albums}" var="album">
    <tr>
      <td>${album.title }</td>
      <td>${album.numDiscs }</td>
      <td><stripes:link href="/albums/edit.jsp">
        <stripes:param name="album.id" value="${album.id }" />
          edit
        </stripes:link></td>
    </tr>
  </c:forEach>
</table>
<stripes:link href="/albums/edit.jsp">new</stripes:link>
