<%-- 
    Document   : AskQuestion
    Created on : Nov 25, 2015, 1:44:34 PM
    Author     : mochamadtry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style> 
            <%@ include file="style.css"%>
        </style>
        <title>Question</title>
    </head>
    <body>
        <div class="container">
            <div id="header">
		<a href="index.php"><h1>Simple StackExchange</h1></a>
            </div>
		<h2> Ask New Question </h2> 
            <div class="main">
		<div class="wrapper" id="question-form">
			<div class="content-header">
				<h2>What's your question?</h2>
			</div>
			<div class="child-content">
				<form action="askquestion" method="POST" id="the-form">
					
					<input type="text" name="topic" placeholder="Question Topic" id="topic" required> <br>
                                        <textarea name="content" placeholder="Content" id="content" required> </textarea> <br>
					<input type="submit" value="Post" name="post" id="post">
				</form>
			</div>
		</div>
	</div>
                
        </div>
    </body>
</html>