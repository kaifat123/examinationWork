# language: ru
  @run @test
Функционал: Обязательное тестовое покрытие

  @severity=blocker
  Сценарий: Просмотр предстоящих мероприятий
    * Пользователь открывает главную страницу
    * Пользователь переходит по вкладке "Events"
    * Пользователь нажимает кнопку "Upcoming events"
    * Пользователь проверяет отображение карточек мероприятий
    * Пользователь сравнивает количество карточек мероприятий и счетчик "Upcoming events"
