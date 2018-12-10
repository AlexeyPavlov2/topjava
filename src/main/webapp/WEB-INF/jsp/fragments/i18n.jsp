<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var i18n = [];

    //https://beginnersbook.com/2013/12/jsp-include-with-parameter-example/

    i18n["addTitle"] = '<spring:message code="${param.target}.add"/>';
    i18n["editTitle"] = '<spring:message code="${param.target}.edit"/>';
    <c:forEach var='key' items='<%=new String[]{"common.deleted", "common.saved", "common.enabled", "common.disabled", "common.errorStatus"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>

    // Не удалось заполнить массивы чтением из ResourceBundle как здесь
    // https://www.mkyong.com/java/java-resourcebundle-example/
    // или здесь
    // https://www.baeldung.com/java-resourcebundle
    // или как делали в basejava

    var dataTableLanguageData = {
        "emptyTable": '<spring:message code="datatable.emptyTable"/>',
        "info": '<spring:message code="datatable.info"/>',
        "infoEmpty": '<spring:message code="datatable.infoEmpty"/>',
        "infoFiltered": '<spring:message code="datatable.infoFiltered"/>',
        "lengthMenu": '<spring:message code="datatable.lengthMenu"/>',
        "loadingRecords": '<spring:message code="datatable.loadingRecords"/>',
        "processing": '<spring:message code="datatable.processing"/>',
        "search": '<spring:message code="datatable.search"/>',
        "zeroRecords": '<spring:message code="datatable.zeroRecords"/>'
    };
</script>
