# language: ru
@run @test
Функционал: Обязательное тестовое покрытие

  @severity=blocker
  Сценарий: Просмотр детальной информации о мероприятии
    * Пользователь открывает главную страницу
    * Пользователь переходит по вкладке "Events"
    * Пользователь нажимает кнопку "Upcoming events"
    * Пользователь проверяет отображение карточек мероприятий
    * Пользователь нажимает на любую карточку
    * Проверить странице с информацией о мероприятии отображение блоков
      | кнопка для регистрации |
      | дата и время           |
      | программа мероприятия  |