<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<style>
.grid-area {
display: grid;
grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
grid-template-areas:
    "defs defs gifs gifs articles"
    "defs defs gifs gifs articles";
}

.grid-area1 {
    grid-area: defs;
}

.grid-area2 {
     grid-area: gifs;
 }

.grid-area3 {
    grid-area: articles;
}
</style>


</head>

<body>

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
                ${article.link}
            </li>
            </c:forEach>
        </ul>
        </div>
</div>

</body>
</html>