<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html lang="en-us">
<head>
    <title>Phrases</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="resources/assets/css/bootstrap.css">
    <script src="resources/assets/js/lib/jquery-2.1.4.min.js"></script>
    <script src="resources/assets/js/lib/bootstrap.js"></script>
    <script src="resources/assets/js/lib/bootbox.min.js"></script>
    <script src="resources/assets/js/lib/knockout-3.3.0.js"></script>
    <script src="resources/assets/js/lib/moment.js"></script>
</head>
<body>

    <div id="main" class="container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">

                <div class="navbar-header">
                  <a class="navbar-brand" href="#">
                    Phrases
                  </a>
                </div>

                <div class = "navbar">
                    <button data-bind="click: beginAdd" class="btn navbar-btn">Add Phrase</button>
                </div>

            </div>
        </nav>

        <table class="table">
            <tbody>
                <!-- ko foreach: phrases -->
                <tr>
                    <td>
                        <p>
                            <b data-bind="text: phrase"></b>
                            <small>
                            <span class="help-block" data-bind="text: date"></span>
                            </small>
                        </p>
                        <p data-bind="text: description"></p>
                    </td>
                    <td>
                         <button data-bind="click: $parent.beginEdit" class="btn">Edit</button>
                         <button data-bind="click: $parent.remove" class="btn">Delete</button>
                    </td>
                </tr>
                <!-- /ko -->
            </tbody>
        </table>
    </div>

    <div id="add" class="modal fade in" tabindex="=1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                    <h3 id="addDialogLabel">Add Phrase</h3>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label" for="inputPhrase">Phrase</label>
                            <div class="controls">
                                <textarea rows="4" style="width: 100%;" data-bind="value: phrase" id="inputPhrase" placeholder="Phrase"></textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="inputDescription">Description</label>
                            <div class="controls">
                                <textarea rows="4" style="width: 100%;" data-bind="value: description" id="inputDescription" placeholder="Description"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-bind="click: addPhrase" class="btn">Add Phrase</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <div id="edit" class="modal fade in" tabindex="=1" role="dialog" aria-labelledby="editDialogLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                    <h3 id="editDialogLabel">Edit Phrase</h3>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <input type="hidden" data-bind="value: id" type="text" id="inputId" >
                        <div class="control-group">
                            <label class="control-label" for="inputPhrase">Phrase</label>
                            <div class="controls">
                                <textarea rows="4" style="width: 100%;" data-bind="value: phrase" id="inputPhrase" placeholder="Phrase"></textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="inputDescription">Description</label>
                            <div class="controls">
                                <textarea rows="4" style="width: 100%;" data-bind="value: description" id="inputDescription" placeholder="Description"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-bind="click:editPhrase" class="btn">Update Phrase</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
            </div>
        </div>
    </div>


    <script src="resources/assets/js/app.js"></script>

</body>
</html>