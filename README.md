# Canteen

## Текущая структура проекта
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
    int calories; //В каллориях
}

class Canteen {
    private ArrayList<Food> menu; //Список всех продуктов для этой столовой
    private HashMap<Integer, ArrayList<Food>> typeToListFood; //Списки продуктов по категориям type
}

class Basket {
    private HashMap<Integer, Integer> idToCount; //Количество продуктов по ID <ID, количество>
}
```
## Необходимо:

*Проект должен находится в папке `project`\
*Используем camelCase, пишем в том же стиле, что уже начал Леша\
*Исправить структуру пректа\
*Распределить задачи\
*Все вспомогательные файлы держать отдельно от `project` в соответствующей директории

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
1)Напишите мне, я добавлю вас как разработчиков
2)Для того, чтобы скопировать папку как компьютер: сначала скопируйте его URL (Clone with SSH не потребует ввода имени пользователя и пароля GitLab, в отличие от Clone with HTTPS), Затем воспользуйтесь командой:
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
*посмотреть статус
```bash
$ git status
```
*подготовить файл к "загрузке"
```bash
$ git add filename
```
*добавить коммит
```bash
$ git commit -m name
```
*загрузить в ветку
Внимение!!!
Это действие необходимо делать аккуратно тк вы меняется структуру проекта у всех.
Лучше делать это в своей ветке, чтобы не испртить ничего другим.
```bash
$ git push
```
*Для перемещения по веткам используйте
```bash
$ git checkout name
```
*Создать новую ветку
```bash
$ git checkout -b new-name
```
*Для загрузки данных с сервера используйте
```bash
$ git pull
```

### Советы по работе с git

Все имеют полные права, поэтому будьте аккуратны!
Работайте в отдельной ветке, пуште только тогда, когда уверены, что все будет работать нормально.
Сливайте ветки только по совместной договоренности.
