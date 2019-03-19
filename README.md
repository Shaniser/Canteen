# Canteen
## Для пользователя
Спасибо, что уделили внимание нашему проекту!
>**Обратите внимание!**
>Данный проект разрабатывался в рамках мероприятия "Best Hack 2019" и предоставляется в пользование исключительно в ознакомительных целях.
>Полное лицензионное соглашение можно прочитать <a href="https://github.com/Shaniser/Canteen/tree/master/license agreement(RUS)">здесь.</a>

## Установка
1. Скачайте .apk файл данного приложения ***добавить apk***
2. Следуйте дальнейшим инструкциям при установке
## Быстрый старт
**После установки и запуска приложения перед вами будет список столовых.**
>Нажмите на столовую, чтобы выбрать ее\
**После выбора столовой перед вами откроется меню столовой.**
* В верхней части экрана находится рассписание
* В средней части экрана находятся фильтры выбора блюд
>Выберете пункт в сплывающем меню, чтобы применить фильтр\
* В нижней части экрана находится меню столовой
**Выберете интересующее блюдо из меню**
>Для того, чтобы посмотреть подробную информацию о блюде, нажмте на него\
**Вы можете добавить блюдо в корзину, нажав на кнопку "+"**
>Чтобы регулировать количество порций используйте "+" и "-"\
**В Корзине вы можете еще раз посмотреть информацию о выбранных вами блюдах, изменить количество порций и узнать итоговую цену**
>Нажмите на изображение корзины в правом верхнем углу, чтобы перейти в корзину\
###### Надеимся, что вам понравится наше приложение!
#### Команда разработчиков
* Алексей Костюченко,   [ВК](https://vk.com/shaniser)
* Михаил Соколовский,   [ВК](https://vk.com/sokolmish)
* Илья Щербаков,   [ВК](https://vk.com/ylyxa)
* Илья Каркин,   [ВК](https://vk.com/id210438588)

## Содержание прокета
<ul>
    <li><a href="https://github.com/Shaniser/Canteen/tree/master/app/src/main/java/com/godelsoft/canteen">Основные классы</a>
        <ul>
            <li><a href="https://github.com/Shaniser/Canteen/blob/master/app/src/main/java/com/godelsoft/canteen/MainActivity.java">MainActivity</a> - Точка входа в приложение</li>
            <li><a href="https://github.com/Shaniser/Canteen/blob/master/app/src/main/java/com/godelsoft/canteen/CanteenLoader.java">CanteenLoader</a> - Класс получения данных о столовых и их меню</li>
            <li><a href="https://github.com/Shaniser/Canteen/blob/master/app/src/main/java/com/godelsoft/canteen/CanteenProvider.java">CanteenProvider</a> - Класс, предоставляющий информацию о столовой и меню</li>
        </ul></li>
    <li><a href="https://github.com/Shaniser/Canteen/tree/master/app/src/main/assets/menus">Данные столовых и меню</a></li>
    <li><a href="https://github.com/Shaniser/Canteen/blob/master/dishes.txt">Список используемых блюд</a></li>
</ul>

## Структура хранения информации о столовых
Вся информация об одной столовой содержится в одном текстовом файле, находящемся в папке <a href="https://github.com/Shaniser/Canteen/tree/master/app/src/main/assets/menus">/assets/menus</a>.<br>
Названия всех таких файлов должны быть перечислены в <a href="https://github.com/Shaniser/Canteen/blob/master/app/src/main/assets/canteensList.txt">/assets/canteensList.txt</a> по одному файлу на строку.<br>
В связи с тем, что вся информация хранится в текстовом виде, допускается быстрый переход на другие способы хранения и получения этой информации, в том числе - загрузка с сервера.
<ul>
    <li><a href="https://github.com/Shaniser/Canteen/blob/master/canteenExample.txt">Пример оформления файла с информацией о столовой с комментариями</a> (В рабочем файле комментарии не допускаются)</li>
    <li><a href="https://github.com/Shaniser/Canteen/blob/master/dishes.txt">Пример оформления информации о блюдах</a></li>
</ul>

### Текущая структура проекта (15.03.19)
```java
class Food {
    int id;
    int type;
    String label;
    int weight; //В граммах
    double proteins; //От 0 до 1
    double fats; //От 0 до 1
    double carbohydrates; //От 0 до 1
    boolean vegetarian;
    int cost; //В копейках
    int calories; //В Килокалориях
    String description;
}

class Menu {
    private ArrayList<Food> menu; //Список всех продуктов для этой столовой
    private HashMap<Integer, ArrayList<Food>> typeToListFood; //Списки продуктов по категориям type
}

class Basket {
    private HashMap<Integer, Integer> idToCount; //Количество продуктов по ID <ID, количество>
}
```
### Необходимо:

* Проект должен находится в папке `project`\
* Используем camelCase, пишем в том же стиле, что уже начал Леша\
* Исправить структуру пректа\
* Все вспомогательные файлы держать отдельно от `project` в соответствующей директории\
* Наполнить dishes.txt едой

### Вспомогательные структуры:
``` java
public enum FoodType {
    Snack, //Закуски (+Салаты)
    Soup, //Первые блюда
    Hot, //Вторые блюда
    Garnish, //Гарниры
    Bakery, //Хлебобулочные изделия
    Desert, //Десерты (+Пустыня)
    Drink, //Напитки
}

public class TextListener {
    public TextView textView;

    public TextListener(TextView textView, int listenId) //

    public static void change(int listenId, String text) //

    public void setText(String text) //
}

public class TimeSpan {
    public TimeSpan(int openHour, int openMin, int closeHour, int closeMin) //

    public static TimeSpan fromString(String str) //
    
    public boolean isInInterval(Calendar time) //
    
    public String toString() //
}
```


### Настройка git

Скачайте git.
Для того, чтобы иметь возможность сохранять изменения кода на вашем компьютере на GitHub, нужно
"сообщить" гитлабу каким образом идентифицировать ваш компьютер.
Для этого существует поняние ssh-ключей. Для генерации пары открытый/закрытый ssh-ключ исполните следующие команды.

```bash
$ mkdir -p ~/.ssh
$ chmod 700 ~/.ssh
$ ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/Users/p.bereznoy/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /tmp/test.
Your public key has been saved in /tmp/test.pub.
The key fingerprint is:
SHA256:9Yf53peICFiHtsvlLTU+lnMidP9Vd7U7aWtSE9GWyIs p.bereznoy@msk-wifi-21fap7-p_berezhnoy-noname.mail.msk
The keys randomart image is:
+---[RSA 2048]----+
|            . . o|
|             o oo|
|        . . . ..o|
|       + o E + .o|
|      + S   + ..=|
|     . o o + o o*|
|      . * * = +=+|
|       o = X *.+=|
|          + = =oo|
+----[SHA256]-----+
```
Если вы не уверены, что готовы потратить пару часов на разбирательство "почему оно не работает", не меняйте
путь к ключу, который предлагает утилита (строка "Enter file in which to save the key"), просто нажмите Enter.
Если вам не нужен пароль для ключа (большинству не нужно), тоже просто нажмите Enter.
В результате у вас были сгенерированы 2 файла:

```bash
$ ls ~/.ssh
id_rsa id_rsa.pub
```
Необходимо скопировать содержимое файла id__rsa.pub на гитхаб:
```bash
$ cat ~/.ssh/id_rsa.pub
ssh-rsa
AAAAB3NzaC1yc2EAAAADAQABAAABAQD9nu0UpP/5txdI9CkOkIj3N4A0wdQ8Skm1s6mISmPmq6efOLJH5JEJ3oEOWvFBZOGMzR0QfJ9UOWy02/+YEXAJ9hMKoenaKHovTXhL6i9T99bD9TDouWh9kR4XbDht2pcmEzRkvgKh+xSwqDt7IwShdQtBr93j9H/z5pL38mKOz98TLGEBXDJMOH0QGHk/FPRiVGQl6HxNOa7wGzYR1fMgWMK5qX6S/81dRMOWjgm3QvpUiNwk3POhkLcO5YOV+H3zxb65KzDXixScQBRBWGUqKzc2qoyoG84m7LirGHc5moH+q5Ieo+nC5l0NOd3sKqq5XL5L2ZmNoErM2WVQZKnz
p.bereznoy@msk-wifi-21fap7-p_berezhnoy-noname.mail.msk
```
Далее необходимо скопировать ключ на гитхаб:
Settings->SSH and GPG keys->New SSH key

Просим вас исполнить следующие команды для конфигурирования вашего локального гита.
Настройки принадлежности ваших коммитов:
```bash
$ git config --global user.name "Your Name Surname"
$ git config --global user.email "your@e.mail"
```

### Начало работы
1)Напишите мне, я добавлю вас как разработчиков\
2)Для того, чтобы скопировать папку как компьютер: сначала скопируйте его URL (Clone with SSH не потребует ввода имени пользователя и пароля GitHub, в отличие от Clone with HTTPS), Затем воспользуйтесь командой:
```bash
$ git clone "URL"
```
3)Для создания своей ветки:
```bash
$ git checkout branches-name
$ git push
$ git checkout -b your-name
```
4)Для локальной загрузки изменений (у себя на компьютере):
* посмотреть статус
```bash
$ git status
```
* подготовить файл к "загрузке"
```bash
$ git add filename
```
* добавить коммит
```bash
$ git commit -m name
```
* загрузить в ветку
Внимение!!!
Это действие необходимо делать аккуратно тк вы меняется структуру проекта у всех.
Лучше делать это в своей ветке, чтобы не испртить ничего другим.
```bash
$ git push
```
* Для перемещения по веткам используйте
```bash
$ git checkout name
```
* Создать новую ветку
```bash
$ git checkout -b new-name
```
* Для загрузки данных с сервера используйте
```bash
$ git pull
```

### Советы по работе с git

Все имеют полные права, поэтому будьте аккуратны!
Работайте в отдельной ветке, пуште только тогда, когда уверены, что все будет работать нормально.
Сливайте ветки только по совместной договоренности.
