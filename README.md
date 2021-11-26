# app-currency
http://www.cbr.ru/scripts/XML_daily.asp
Rossiya Markaziy Bankidagi kurs valyutalarni olib ularni bazaga saqlashga mojjalangan dastur.
Malumotlar XML formatda.
Реализовать REST API для получения курсов валют. (CBR)

После запуска проекта.
http://localhost/api/auth/login
Имя пользователя: username:"admin", пароль: "admin";
это даёт вам "Bearer Token";
вы берете и кладете в поле авторизации;
потом все пути будет отрытый;
GET: http://localhost/api/currencies : Возвращает все курсы обмена.
GET: http://localhost/api/currency/{code} : code-> даёте char_code и он возвращает вам курс цена.

