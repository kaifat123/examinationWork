# examinationWork
Курсовая работа

# Используемые параметры
***-Dbrowser*** - возможность кросбраузерного тестирования (доступны: chrome, firefox, opera)

***-DTAGS*** - запуск тестов по тегу например:
```` 
 -DTAGS=@test
````
***-Ddocker*** - запуск удаленного исполнения тестов в Docker-контейнере на удаленной машине

***-Doptions*** - применение опций для браузера, разделитель ";" например:
````
-Doptions=headless;window-size=1920,1080
````

Пример запуска:

````
mvn clean test -Dbrowser=chrome -DTAGS=@test -Ddocker=true
````