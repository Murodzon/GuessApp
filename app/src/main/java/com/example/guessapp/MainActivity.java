package com.example.guessapp;

import static com.example.guessapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;
    int attempts;  // Количество попыток
    int secretKey; // Загаданное число

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        editText = findViewById(id.eText);
        button = findViewById(id.btn);
        textView = findViewById(id.tResult);

        startNewGame();  // Начать новую игру

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();  // Обработка угадывания
            }
        });
    }

    // Метод для начала новой игры
    private void startNewGame() {
        attempts = 3;  // Устанавливаем количество попыток
        Random random = new Random();
        secretKey = random.nextInt(20) + 1;  // Генерируем новое загаданное число
        Log.i("Result", secretKey + "");  // Логирование загаданного числа для отладки
        textView.setText("Попробуйте угадать число от 1 до 20");  // Начальный текст
        button.setText("Guess");  // Восстанавливаем текст кнопки
        button.setEnabled(true);  // Включаем кнопку
        editText.setText("");  // Очищаем поле ввода
    }

    // Метод обработки угадывания
    private void handleGuess() {
        if (attempts > 0) {  // Проверка, есть ли еще попытки
            String value = editText.getText().toString();
            if (!value.isEmpty()) {
                int inValue = Integer.parseInt(value);

                if (inValue == secretKey) {
                    textView.setText("Вы выиграли! Загаданное число: " + secretKey + "\nИграть еще раз?");
                    button.setText("Играть еще раз");  // Меняем текст кнопки
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startNewGame();  // Начать новую игру
                        }
                    });
                } else {
                    attempts--;  // Уменьшаем количество попыток
                    if (attempts > 0) {
                        if (inValue > secretKey) {
                            textView.setText("Ваше число больше! Осталось попыток: " + attempts);
                        } else {
                            textView.setText("Ваше число меньше! Осталось попыток: " + attempts);
                        }
                    } else {
                        textView.setText("Вы проиграли! Загаданное число было: " + secretKey + "\nИграть еще раз?");
                        button.setText("Играть еще раз");  // Меняем текст кнопки
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startNewGame();  // Начать новую игру
                            }
                        });
                    }
                }
            } else {
                textView.setText("Пожалуйста, введите допустимое число!");
            }
        }
    }
}
