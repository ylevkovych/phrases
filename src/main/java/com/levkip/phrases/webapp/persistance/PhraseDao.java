package com.levkip.phrases.webapp.persistance;

import com.levkip.phrases.domain.Phrase;

import java.util.List;


public interface PhraseDao {

    List<Phrase> getPhrases();

    List<Phrase> searchPhrase(String searchPhrase, boolean whole);

    Phrase getPhrase(Long id);

    Long addPhrase(Phrase phrase);

    boolean updatePhrase(Phrase phrase, Long id);

    boolean deletePhrase(Long id);
}
