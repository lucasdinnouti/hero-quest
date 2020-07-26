package br.unicamp.hero.quest.service.input;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.InterfaceText;
import java.io.InputStream;
import java.util.*;

public interface InputService {
    Command readCommand();
    Command getLastCommand();
    String getChoice(Set<String> options);
}