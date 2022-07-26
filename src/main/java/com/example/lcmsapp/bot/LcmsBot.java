package com.example.lcmsapp.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class LcmsBot extends TelegramLongPollingBot {
    private final TelegramServiceImpl telegramService;
    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText(); //SALOM /start
                if (text.equals("/start")) {
                    execute(telegramService.welcome(update));
                }else{
                    //qanaqadr string oliy o'rta
                    //tekshirish kk qaysi boshqichda ekanligini
                    execute(telegramService.sendDiplom(update));
                }
            } else if (message.hasContact()) {
                execute(telegramService.shareContact(update));
            } else if (message.hasLocation()) {
                execute(telegramService.shareLocation(update));
            } else {
            }


        } else if (update.hasCallbackQuery()) {

        } else {

        }


    }
}
