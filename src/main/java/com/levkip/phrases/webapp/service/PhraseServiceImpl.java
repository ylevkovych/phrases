package com.levkip.phrases.webapp.service;

import com.levkip.phrases.domain.Phrase;
import com.levkip.phrases.webapp.persistance.PhraseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseServiceImpl implements PhraseService {


    private PhraseDao phraseDao;
    @Autowired
    public void setPhraseService(PhraseDao phraseDao)
    {
        this.phraseDao = phraseDao;
    }

    @Override
    public List<Phrase> getPhrases() {
        return phraseDao.getPhrases();
    }

    @Override
    public List<Phrase> searchPhrase(String searchPhrase, boolean whole) {
        return phraseDao.searchPhrase(searchPhrase, whole);
    }

    @Override
    public Phrase getPhrase(Long id) {
        return phraseDao.getPhrase(id);
    }

    @Override
    public Long addPhrase(Phrase phrase) {
        return phraseDao.addPhrase(phrase);
    }

    @Override
    public boolean updatePhrase(Phrase phrase, Long id) {
        return phraseDao.updatePhrase(phrase, id);
    }

    @Override
    public boolean deletePhrase(Long id) {
        return phraseDao.deletePhrase(id);
    }
}
