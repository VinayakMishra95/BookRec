<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookRec</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #4CAF50;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #4CAF50;
        }

        select, input[type="text"] {
            width: 200px;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type="submit"], button[type="button"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover, button[type="button"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1>BookRec Homepage</h1>
    <form action="search" method="POST">
        <label for="options">Search for:</label>
        <select id="options" name="options">
            <option value="book">Book</option>
            <option value="author">Author</option>
            <option value="user">User</option>
        </select>
        <br>
        <input type="text" id="search" name="search" placeholder="Enter your search">
        <br>
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

