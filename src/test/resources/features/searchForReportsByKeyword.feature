#language: ru
@run @test
Функционал: Обязательное тестовое покрытие

  @severity=blocker
  Сценарий: Поиск докладов по ключевому слову
    * Пользователь открывает главную страницу
    * Пользователь переходит по вкладке "Video"
    * Пользователь вводит ключевое слово "QA" в поле поиска
    * На странице отображаются доклады, содержащие в названии ключевое слово поиска

