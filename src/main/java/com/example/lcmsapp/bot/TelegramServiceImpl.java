package com.example.lcmsapp.bot;

import com.example.lcmsapp.entity.User;
import com.example.lcmsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {
    private final UserRepository userRepository;

    @Override
    public SendMessage welcome(Update update) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(new KeyboardRow(Collections.singleton(KeyboardButton.builder().requestContact(true).text("Raqamni jo'natish").build())));

        Long chatId = update.getMessage().getChatId();

        Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(chatId));
        if (optionalUser.isEmpty()) {
            //ro'yxatga olishim kk
            userRepository.save(User.builder().chatId(String.valueOf(chatId)).build());
        } else {
            User user = optionalUser.get();
        }

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return SendMessage.builder().replyMarkup(replyKeyboardMarkup).chatId(String.valueOf(chatId)).text("Xush kelibsiz!" + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + "\n" + "Raqamni jo'natish tugmasini bosing").build();

    }

    @Override
    public SendMessage shareContact(Update update) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(new KeyboardRow(Collections.singleton(KeyboardButton.builder().requestLocation(true).text("Lokatsiya jo'natish").build())));

        Contact contact = update.getMessage().getContact();
        Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        User user = optionalUser.get();
        user.setPhone(contact.getPhoneNumber());
        userRepository.save(user);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return SendMessage.builder()
                .replyMarkup(replyKeyboardMarkup)
                .text("Lokatsiya jo'nating")
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .build();
    }

    @Override
    public SendMessage shareLocation(Update update) {
        Location location = update.getMessage().getLocation();
        Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
        User user = optionalUser.get();
        user.setLat(location.getLatitude());
        user.setLon(location.getLongitude());
        userRepository.save(user);
        return SendMessage.builder()
                .text("Ma'lumotingiz?")
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .build();
    }

    @Override
    public SendMessage sendDiplom(Update update) {
        return null;
    }
}
