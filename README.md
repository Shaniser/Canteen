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
[1]: images/1.jpg "1"
[2]: images/2.jpg "2"
[3]: images/3.jpg "3"
[4]: images/4.jpg "4"
[5]: images/5.jpg "5"
[6]: images/6.jpg "6"
[7]: images/7.jpg "7"


**После установки и запуска приложения перед вами будет список столовых.**
>Нажмите на столовую, чтобы выбрать ее

![][1]

**После выбора столовой перед вами откроется меню столовой.**

![][2]

* В верхней части экрана находится рассписание
* В средней части экрана находятся фильтры выбора блюд
>Выберете пункт в сплывающем меню, чтобы применить фильтр

![][3]

* В нижней части экрана находится меню столовой

**Выберете интересующее блюдо из меню**
>Для того, чтобы посмотреть подробную информацию о блюде, нажмте на него

![][4]

**Вы можете добавить блюдо в корзину, нажав на кнопку "+"**
>Чтобы регулировать количество порций используйте "+" и "-"

![][5]

**В Корзине вы можете еще раз посмотреть информацию о выбранных вами блюдах, изменить количество порций и узнать итоговую цену**
>Нажмите на изображение корзины в правом верхнем углу, чтобы перейти в корзину

![][6]

**Чтобы сразу очистить всю корзину нижмите "очистить корзину"**

![][7]

#### Надеимся, что вам понравится наше приложение!
### Команда разработчиков
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
