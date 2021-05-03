<%@ page import="models.User" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white mb-4">

    <a class="navbar-brand" href="#"><img class="img-logo" src="media/norges_roforbund.png"></a>

    <%

        User user = (User) session.getAttribute("user");
        if (user != null) {
            out.println("    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\"\n" +
                    "            aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                    "        <span class=\"navbar-toggler-icon\"></span>\n" +
                    "    </button>\n" +
                    "\n" +
                    "\n" +
                    "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                    "\n" +
                    "        <ul class=\"navbar-nav mr-auto ml-auto my-2\">\n" +
                    "\n" +
                    "            <li class=\"nav-item active\">\n" +
                    "                <a class=\"nav-link\" href=\"UserList\">Users</a>\n" +
                    "            </li>\n" +
                    "\n" +
                    "            <li class=\"nav-item active\">\n" +
                    "                <a class=\"nav-link\" href=\"TestdataList\">Testdata</a>\n" +
                    "            </li>\n" +
                    "\n" +
                    "            <li class=\"nav-item dropdown\">\n" +
                    "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\"\n" +
                    "                   aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                    "                    Database\n" +
                    "                </a>\n" +
                    "                <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n" +
                    "                    <a class=\"dropdown-item\" href=\"RoleCreate\">Roles</a>\n" +
                    "                    <div class=\"dropdown-divider\"></div>\n" +
                    "                    <a class=\"dropdown-item\" href=\"ClubCreate\">Clubs</a>\n" +
                    "                    <div class=\"dropdown-divider\"></div>\n" +
                    "                    <a class=\"dropdown-item\" href=\"ClassCreate\">Classes</a>\n" +
                    "                    <div class=\"dropdown-divider\"></div>\n" +
                    "                    <a class=\"dropdown-item\" href=\"WeekCreate\">Weeks</a>\n" +
                    "                    <div class=\"dropdown-divider\"></div>\n" +
                    "                    <a class=\"dropdown-item\" href=\"TestCreate\">Tests</a>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "\n" +
                    "        </ul>\n" +
                    "\n" +
                    "        <ul class=\"navbar-nav ml-auto my-2\">\n" +
                    "            <li class=\"nav-item active\">\n" +
                    "                <div class=\"cover\">\n" +
                    "                    <a class=\"nav-link\" href=\"UserProfile\">\n" +
                    "                        " + user.getFirst_name() + " " + user.getLast_name() + "\n" +
                    "                        <img class=\"rounded-circle img-cover\" src=\"data:image/jpg;base64, " + user.getImage() + "\"\n" +
                    "                             onerror=\"this.onerror=null; this.src='media/user.jpg'\">\n" +
                    "                    </a>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "        </ul>\n" +
                    "\n" +
                    "        <form class=\"ml-auto mb-0\" action=\"Logout\">\n" +
                    "            <button class=\"button-secondary\" type=\"submit\">Logout</button>\n" +
                    "        </form>\n" +
                    "\n" +
                    "    </div>");
        }
    %>
</nav>
<h1 class="text-center text-success"><span id="message">${param.message}</span></h1>
<h1 class="text-center text-danger"><span id="errorMessage">${param.errorMessage}</span></h1>
