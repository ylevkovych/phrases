package com.levkip.phrases.webapp.service;


import com.levkip.phrases.domain.Phrase;

import java.util.List;

public interface PhraseService {

    List<Phrase> getPhrases();

    List<Phrase> searchPhrase(String searchPhrase, boolean whole);

    Phrase getPhrase(Long id);

    Long addPhrase(Phrase phrase);

    boolean updatePhrase(Phrase phrase, Long id);

    boolean deletePhrase(Long id);
}
