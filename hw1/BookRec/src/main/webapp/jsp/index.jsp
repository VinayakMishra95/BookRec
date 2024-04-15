<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookRec</title>
</head>
<body>
    <h1>BookRec Homepage</h1>
    <form method="GET">
        <label for="options">Search for:</label>
        <select id="options" name="options">
            <option value="book">Book</option>
            <option value="author">Author</option>
            <option value="user">User</option>
        </select>
        <input type="text" id="search" name="search">
        <button type="submit">Search</button>
    </form>
    <form method="POST">
        <a href="html/login.html">
        <button type="button">Log in</button>
        </a>
        <a href="html/signup.html">
        <button type="button">Sign up</button>
        </a>
    </form>
</body>
</html>
