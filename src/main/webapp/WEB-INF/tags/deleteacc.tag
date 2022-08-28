<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="deleteM.do" method="post">
                     <input type="hidden" name="action" value="deleteM">
                     <input type="hidden" name = "mid" value = "${uid}">
                     
                     비밀번호 : <input type="password" name="mpw" placeholder = "비밀번호 확인">
                     <input type="submit" value="회원탈퇴">
                  </form>
