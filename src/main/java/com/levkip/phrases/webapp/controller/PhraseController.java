package com.levkip.phrases.webapp.controller;


import com.levkip.phrases.domain.Phrase;
import com.levkip.phrases.webapp.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class PhraseController extends HandleController {

    private PhraseService phraseService;
    @Autowired
    public void setPhraseService(PhraseService phraseService)
    {
        this.phraseService = phraseService;
    }

    @RequestMapping(value = "rest/api/v1.0/phrase", method = RequestMethod.GET)
    @ResponseBody
    public List<Phrase> getPhrases() {
        return phraseService.getPhrases();
    }

//    @RequestMapping(value = "rest/v1/phrase?search=", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Phrase> searchPhrase(String searchPhrase, boolean whole) {
//        return phraseService.searchPhrase(searchPhrase, whole);
//    }

    @RequestMapping(value = "rest/api/v1.0/phrase/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Phrase getPhrase(@PathVariable Long id) {
        return phraseService.getPhrase(id);
    }

    @RequestMapping(value = "rest/api/v1.0/phrase", method = RequestMethod.POST)
    @ResponseBody
    public Long addPhrase(@RequestBody Phrase phrase) {
        return phraseService.addPhrase(phrase);
    }

    @RequestMapping(value = "rest/api/v1.0/phrase/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updatePhrase(@RequestBody Phrase phrase, @PathVariable Long id) {
        return phraseService.updatePhrase(phrase, id);
    }

    @RequestMapping(value = "rest/api/v1.0/phrase/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deletePhrase(@PathVariable Long id) {
        return phraseService.deletePhrase(id);
    }

}
