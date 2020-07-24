package br.unicamp.hero.quest.service.input;

import br.unicamp.hero.quest.constant.Command;
import br.unicamp.hero.quest.constant.InterfaceText;
import java.io.InputStream;
import java.util.Scanner;

public interface InputService {
    Command readCommand();
    Command getLastCommand();
}