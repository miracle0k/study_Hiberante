<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<stripes:useActionBean 
  beanclass="com.oreilly.hh.web.AlbumActionBean" 
  var="actionBean" event="edit" />
<h1>Album Edit Page</h1>
<stripes:form action="/Album.action" >
  <stripes:errors />
  <stripes:hidden name="album.id"></stripes:hidden>
  <table>
     <tr>
         <td>Title:</td>
         <td><stripes:text name="album.title" /></td>
     </tr>
     <tr>
         <td>Discs:</td>
         <td><stripes:text name="album.numDiscs" /></td>
       </tr>
    </table>
 
    <h2>Album Comments</h2>
      <c:choose>
        <c:when test="${actionBean.album.id != null }">
          <stripes:link href="/albums/edit_comment.jsp">
             <stripes:param name="album.id" value="${actionBean.album.id }" />
             Add A Comment
          </stripes:link>
          <c:if test="${empty actionBean.album.comments}" >
          There are no album comments yet.
          </c:if>
        </c:when>
        <c:otherwise>
           Please add the album before entering comments.
        </c:otherwise>
      </c:choose>
    <ul>
    <c:forEach items="${actionBean.album.comments }" var="comment">
       <li>${comment }</li>
    </c:forEach>
    </ul>
  <br />
  <stripes:submit name="save" value="Save"></stripes:submit>
</stripes:form>