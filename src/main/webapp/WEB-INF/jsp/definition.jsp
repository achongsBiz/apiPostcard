<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>Postcards from the Internet</title>
<link rel="stylesheet" type="text/css" href="css/style.css"></head>
</head>
<body>

<form id='search-form' action='/postcard' method='POST'>
    <span>Search Term:</span> <input type='text' name='word'> <input type='submit' value='Search'/>
</form>

<div id='main'>

   <div class = 'info-block'>Viewing results for search term: <span id='current-search-term'>${currentSearch}</span></div>

   <c:if test = "${definitions.size() > 0}">
        <div class = "info-block">
            <H2>According to the dictionary:</H2>
            <c:forEach var="definition" items="${definitions}">
            (${definition.partOfSpeech})  ${definition.definitions}
            <br>
            <br>
            </c:forEach>
            <table>
                <tr><td class='att-title'>API Used:</td><td class='att-name'>Oxford Dictionary API</td></tr>
                <tr><td colspan='2' class='att-url'>https://developer.oxforddictionaries.com/</td></tr>
            </table>
            <hr/>
        </div>



    </c:if>

   <c:if test = "${gifs.size() > 0}">
        <div class = "info-block">
            <H2>According to the internet:</H2>
            <div>
                <c:forEach var="gif" items="${gifs}">
                    <img src="${gif.gifURL}" alt="Couldn't Render"/>
                </c:forEach>
            </div>
            <br>
              <table>
                <tr><td class='att-title'>API Used:</td><td class='att-name'>Giphy API</td></tr>
                <tr><td colspan='2' class='att-url'>https://developers.giphy.com/</td></tr>
              </table>
              <hr/>
        </div>
    </c:if>

    <c:if test = "${articles.size() > 0}">
    <div class = "info-block">
         <H2>According to the news:</H2>
         <ul>
            <c:forEach var="article" items="${articles}">
            <li>
                <a href='${article.link}' target="_blank">${article.link}</a>
            </li>
            </c:forEach>
        </ul>
        <table>
            <tr><td class='att-title'>API Used:</td><td class='att-name'>News API</td></tr>
            <tr><td colspan='2' class='att-url'>https://newsapi.org/</td></tr>
        </table>
        <hr/>
     </div>
    </c:if>
</div>

</body>
</html>