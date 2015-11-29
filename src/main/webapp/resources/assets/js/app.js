//var base_url = "http://localhost:8080/phrases/";
var base_url = "http://app.thelevkip.com/phrases/";

function PhrasesViewModel() {
    var self = this;
    self.phrases_url = base_url + "rest/api/v1.0/phrase";
    self.phrases = ko.observableArray();

    self.ajax = function(url, method, data) {
        var request = {
            url: url,
            type: method,
            contentType: "application/json; charset=utf-8",
            cache: false,
            dataType: "json",
            data: JSON.stringify(data)
        };
        return $.ajax(request);
    };

    self.beginAdd = function() {
        $("#add").modal("show");
    };
    self.add = function(phrase) {
        var url = self.phrases_url;
        self.ajax(url, "POST", phrase).done(function(data) {
            self.phrases.push({
                id: ko.observable(data),
                date: ko.observable(formatTime((new Date()).getTime())),
                phrase: ko.observable(phrase.phrase),
                description: ko.observable(phrase.description)
            });
        });
    };

    self.beginEdit = function(phrase) {
        editPhraseViewModel.setPhrase(phrase);
        $('#edit').modal('show');
    };

    self.edit = function(oldPhrase, phrase) {
        var url = self.phrases_url + "/" + phrase.id;
        self.ajax(url, "PUT", phrase).done(function(data) {
            if (data == true) {
                self.updatePhrase(oldPhrase, phrase);
            }
        });
    };
    self.updatePhrase = function(oldPhrase, newPhrase) {
        var i = self.phrases.indexOf(oldPhrase);
        self.phrases()[i].date(formatTime((new Date()).getTime()));
        self.phrases()[i].phrase(newPhrase.phrase);
        self.phrases()[i].description(newPhrase.description);
    };

    self.remove = function(phrase) {
        bootbox.dialog({
            message: "Are you sure you want to delete phrase?",
            buttons: {
                cancel: {
                    label: "Cancel",
                    className: "btn-default"
                },
                danger: {
                    label: "DELETE",
                    className: "btn",
                    callback: function() {
                        var url = self.phrases_url + "/" + phrase.id();
                        self.ajax(url, "DELETE", null).done(function(data) {
                            if (data == true) {
                                self.phrases.pop(phrase);
                            }
                        });
                    }
                }
            }
        });
    };

    self.ajax(self.phrases_url, 'GET').done(function(data) {
        for (var i = 0; i < data.length; i++) {
            self.phrases.push({
                id: ko.observable(data[i].id),
                date: ko.observable(formatTime(data[i].date)),
                phrase: ko.observable(data[i].phrase),
                description: ko.observable(data[i].description)
            });
        }
    });

};

function AddPhraseViewModel() {
    var self = this;
    self.phrase = ko.observable();
    self.description = ko.observable();

    self.addPhrase = function() {
        $('#add').modal('hide');
        phrasesViewModel.add({
            phrase: self.phrase(),
            description: self.description()
        });
        self.phrase("");
        self.description("");
    }
};

function EditPhraseViewModel() {
    var self = this;
    self.id = ko.observable();
    self.phrase = ko.observable();
    self.description = ko.observable();

    self.setPhrase = function(phrase) {
        self.old_phrase = phrase;
        self.id(phrase.id());
        self.phrase(phrase.phrase());
        self.description(phrase.description());
    }

    self.editPhrase = function() {
        $('#edit').modal('hide');
        phrasesViewModel.edit(self.old_phrase, {
            id: self.id(),
            phrase: self.phrase(),
            description: self.description()
        });
    }
};

function formatTime(millis) {
    return moment(millis).fromNow();
};

var phrasesViewModel = new PhrasesViewModel();
var addPhraseViewModel = new AddPhraseViewModel();
var editPhraseViewModel = new EditPhraseViewModel();

ko.applyBindings(phrasesViewModel, $("#main")[0]);
ko.applyBindings(addPhraseViewModel, $("#add")[0]);
ko.applyBindings(editPhraseViewModel, $("#edit")[0]);