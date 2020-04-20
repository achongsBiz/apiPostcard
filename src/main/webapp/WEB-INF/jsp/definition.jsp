<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>

</head>

<body>

<form action='/postcard' method='POST'>
    <span>Search Term:</span> <input type='text' name='word'> <input type='submit' value='Search'/>
</form>

<div class="grid-area">
    <div class = "grid-area-1">
        <H2>According to the dictionary:</H2>
        <c:forEach var="definition" items="${definitions}">
        (${definition.partOfSpeech})  ${definition.definitions}
        <br>
        <br>
        </c:forEach>
    </div>

    <div class = "grid-area-2">
        <H2>According to the internet:</H2>
        <c:forEach var="gif" items="${gifs}">
            <img src="${gif.gifURL}" alt="Couldn't Render"/>
        </c:forEach>
    </div>

    <div class = "grid-area-3">
         <H2>According to the news:</H2>
         <ul>
            <c:forEach var="article" items="${articles}">
            <li>
                <a href='${article.link}' target="_blank">${article.link}</a>
            </li>
            </c:forEach>
        </ul>
        </div>
</div>

</body>
</html>