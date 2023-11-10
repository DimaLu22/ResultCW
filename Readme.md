## Итоговая контрольная работа по блоку специализация

1. Использование команды cat в Linux

    Создать два текстовых файла: "Pets"(Домашние животные) и "Pack animals"(вьючные животные), используя команду `cat` в терминале Linux. В первом файле перечислить собак, кошек и хомяков. Во втором — лошадей, верблюдов и ослов.

    Объединить содержимое этих двух файлов в один и просмотреть его содержимое.

    Переименовать получившийся файл в "Human Friends".

2. Работа с директориями в Linux
   -Создать новую директорию и переместить туда файл "Human Friends".

    ![Выполнение:](/images/linuxwork.jpg)

3. Работа с MySQL в Linux. “Установить MySQL на вашу вычислительную машину”.
   Подключить дополнительный репозиторий MySQL и установить один из пакетов из этого репозитория.
4. Управление deb-пакетами
   Установить и затем удалить deb-пакет, используя команду `dpkg`.
 
    ![Linux:](/images/linux1.jpg)
    ![Linux:](/images/linux2.jpg)

5.    CREATE DATABASE IF NOT EXISTS `human_friends`;
      USE `human_friends`;

      -- Создаем таблицу "animals"
      CREATE TABLE `animals` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
      animals_class VARCHAR(30)
      );

      -- Создаем таблицы животных с внешним ключем на таблицу animals
      CREATE TABLE `dogs` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      CREATE TABLE `cats` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      CREATE TABLE `hamsters` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      CREATE TABLE `horses` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      CREATE TABLE `camels` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      CREATE TABLE `donkeys` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      FOREIGN KEY (`animal_class_id`) REFERENCES `animals` (`id`) ON DELETE CASCADE
      );

      -- Заполняем таблицу "animals"
      INSERT INTO `human_friends`.`animals` (`id`, `animals_class`) VALUES ('1', 'pet'),('2', 'pack_animals');

      -- Заполняем таблицу "dogs"
      INSERT INTO `human_friends`.`dogs` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Meri', 'Sit, Stay, Fetch', '2019-06-01', 1);

      -- Заполняем таблицу "cats"
      INSERT INTO `human_friends`.`cats` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Musia', 'Purr, Pounce', '2018-04-25', 1);

      -- Заполняем таблицу "hamsters"
      INSERT INTO `human_friends`.`hamsters` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Dunduk', 'Run on the wheel', '2021-05-10', 1);

      -- Заполняем таблицу "horses"
      INSERT INTO `human_friends`.`horses` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Damask', 'Gallop, Jump', '2020-07-20', 2);

      -- Заполняем таблицу "camels"
      INSERT INTO `human_friends`.`camels` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Fast', 'Carry loads, Long trek', '2015-03-17', 2);

      -- Заполняем таблицу "donkeys"
      INSERT INTO `human_friends`.`donkeys` (`name`, `skills`, `birth_date`, `animal_class_id`) VALUES
      ('Ia', 'Carry load, Braying', '2015-04-17', 2);

      -- Удалить таблицу "camels"
      DROP TABLE `human_friends`.`camels`;

      -- Объединить таблицы "horses", и "donkeys" в одну таблицу

      CREATE TABLE `horses_and_donkeys` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      `species` VARCHAR(10) NOT NULL,
      PRIMARY KEY (`id`)
      );
       -- Вставляем данные  в таблицу "horses_and_donkeys"
      INSERT INTO `horses_and_donkeys` (`name`, `skills`, `birth_date`, `animal_class_id`, `species`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'Horse' AS `species`
      FROM `horses`;

      INSERT INTO `horses_and_donkeys` (`name`, `skills`, `birth_date`, `animal_class_id`, `species`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'Donkey' AS `species`
      FROM `donkeys`;

      -- Создаем новую таблицу "young_animals"
      CREATE TABLE `young_animals` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     `name` VARCHAR(50) NOT NULL,
     `species` VARCHAR(20) NOT NULL,
     `age_months` INT NOT NULL
      );

      -- Вставляем данные из таблиц `dogs`, `cats`, `donkeys`, `hamsters`, и `horses` в таблицу `young_animals`
      INSERT INTO `young_animals` (`name`, `species`, `age_months`)
      SELECT `name`, 'Dog' AS `species`, TIMESTAMPDIFF(MONTH, `birth_date`, CURDATE()) AS `age_months`
      FROM `dogs`
      WHERE `birth_date` <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND `birth_date` >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

      INSERT INTO `young_animals` (`name`, `species`, `age_months`)
      SELECT `name`, 'Cat' AS `species`, TIMESTAMPDIFF(MONTH, `birth_date`, CURDATE()) AS `age_months`
      FROM `cats`
      WHERE `birth_date` <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND `birth_date` >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

      INSERT INTO `young_animals` (`name`, `species`, `age_months`)
      SELECT `name`, 'Donkey' AS `species`, TIMESTAMPDIFF(MONTH, `birth_date`, CURDATE()) AS `age_months`
      FROM `donkeys`
      WHERE `birth_date` <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND `birth_date` >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

      INSERT INTO `young_animals` (`name`, `species`, `age_months`)
      SELECT `name`, 'Hamster' AS `species`, TIMESTAMPDIFF(MONTH, `birth_date`, CURDATE()) AS `age_months`
      FROM `hamsters`
      WHERE `birth_date` <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND `birth_date` >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

      INSERT INTO `young_animals` (`name`, `species`, `age_months`)
      SELECT `name`, 'Horse' AS `species`, TIMESTAMPDIFF(MONTH, `birth_date`, CURDATE()) AS `age_months`
      FROM `horses`
      WHERE `birth_date` <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) AND `birth_date` >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

      -- Создаем новую таблицу "all_animals"
      CREATE TABLE `all_animals` (
      id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
      `name` VARCHAR(50) NOT NULL,
      `skills` VARCHAR(100) NOT NULL,
      `birth_date` DATE NOT NULL,
      `animal_class_id` INT UNSIGNED NOT NULL,
      `source_table` VARCHAR(20) NOT NULL,
      PRIMARY KEY (`id`)
      );

      -- Вставляем данные из таблиц `dogs`, `cats`, `donkeys`, `hamsters`, и `horses` в таблицу `all_animals`
      INSERT INTO `all_animals` (`name`, `skills`, `birth_date`, `animal_class_id`, `source_table`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'dogs' AS `source_table`
      FROM `dogs`;

      INSERT INTO `all_animals` (`name`, `skills`, `birth_date`, `animal_class_id`, `source_table`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'cats' AS `source_table`
      FROM `cats`;

      INSERT INTO `all_animals` (`name`, `skills`, `birth_date`, `animal_class_id`, `source_table`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'donkeys' AS `source_table`
      FROM `donkeys`;

      INSERT INTO `all_animals` (`name`, `skills`, `birth_date`, `animal_class_id`, `source_table`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'hamsters' AS `source_table`
      FROM `hamsters`;

      INSERT INTO `all_animals` (`name`, `skills`, `birth_date`, `animal_class_id`, `source_table`)
      SELECT `name`, `skills`, `birth_date`, `animal_class_id`, 'horses' AS `source_table`
      FROM `horses`;


6. Программа-реестр домашних животных находится в папке [](/src)