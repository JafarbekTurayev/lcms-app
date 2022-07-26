package com.example.lcmsapp.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramService {

    SendMessage welcome(Update update);

    SendMessage shareContact(Update update);

    SendMessage shareLocation(Update update);

    SendMessage sendDiplom(Update update);
}
