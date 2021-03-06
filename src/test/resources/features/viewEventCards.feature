#language: ru
@run @test
Функционал: Обязательное тестовое покрытие

  @severity=blocker
  Сценарий: Просмотр карточек мероприятий
    * Пользователь открывает главную страницу
    * Пользователь переходит по вкладке "Events"
    * Пользователь нажимает кнопку "Upcoming events"
    * Пользователь проверяет отображение карточек мероприятий
    * Проверить, что в карточке информация о "место проведения" присутствует
    * Проверить, что в карточке информация о "язык" присутствует
    * Проверить, что в карточке информация о "название мероприятия" присутствует
    * Проверить, что в карточке информация о "дата мероприятия" присутствует
    * Проверить, что в карточке информация о "информация о регистрации" присутствует
    * Проверить, что в карточке информация о "список спикеров" присутствует