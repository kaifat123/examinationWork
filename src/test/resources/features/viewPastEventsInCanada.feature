#language: ru
@run @test
Функционал: Обязательное тестовое покрытие

  @severity=blocker
  Сценарий: Просмотр прошедших мероприятий в Канаде
    * Пользователь открывает главную страницу
    * Пользователь переходит по вкладке "Events"
    * Пользователь нажимает кнопку "Past Events"
    * Пользователь нажимает на фильтр локации, вводит в поиск "Canada" и производит выбор локации
    * Пользователь сравнивает количество карточек мероприятий и счетчик "Past Events"

