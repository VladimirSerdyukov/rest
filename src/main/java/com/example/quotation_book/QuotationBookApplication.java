package com.example.quotation_book;
/*
1. Концептуальный парсинг данных с сайта с помощью пакета jsoup
2. Расширение возможностей парсера для получение конкретной
страницы выдачи, а также конкретной цитаты
3. Продумывание структуры проекта
4. Создание контейнеров базы данных
5. Создание конфигов подключения к базе вместе со слоем данных
6. Создание сервиса для управления данными
7. Написание контроллера для свободного получения цитат по странице,
конкретную по id
8. Написание контроллера бота Telegram с сохранением юзера в
соответствующую таблицу, методы итерирования “вперед”/”назад” по
цитатам в интерфейсе бота
* */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuotationBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuotationBookApplication.class, args);
	}
}
