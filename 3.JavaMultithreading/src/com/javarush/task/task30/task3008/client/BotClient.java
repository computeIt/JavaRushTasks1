package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by ShkerdinVA on 16.05.2017.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String senderName = message.substring(0, message.indexOf(":"));
                String textMessage = message.substring(message.indexOf(":") + 2);
                switch (textMessage) {
                    case "дата":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime())));
                        break;
                    case "день":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("d").format(Calendar.getInstance().getTime())));
                        break;
                    case "месяц":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime())));
                        break;
                    case "год":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime())));
                        break;
                    case "время":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime())));
                        break;
                    case "час":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("H").format(Calendar.getInstance().getTime())));
                        break;
                    case "минуты":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("m").format(Calendar.getInstance().getTime())));
                        break;
                    case "секунды":
                        sendTextMessage(String.format("Информация для %s: %s", senderName,
                                new SimpleDateFormat("s").format(Calendar.getInstance().getTime())));
                        break;
                }
            }
        }

        private String getAnswerFormat(String data) {
            switch(data) {
                case "дата":
                    return "d.MM.YYYY";
                case "день":
                    return "d";
                case "месяц":
                    return "MMMM";
                case "год":
                    return "YYYY";
                case "время":
                    return "H:mm:ss";
                case "час":
                    return "H";
                case "минуты":
                    return "mm";
                case "секунды":
                    return "ss";
                default:
                    return null;
            }
        }
    }

    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    protected String getUserName() {
        return "date_bot_" + (int) (100 * Math.random());
    }

    public static void main(String... args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
