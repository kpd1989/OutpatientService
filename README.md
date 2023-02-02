# Амбулаторная служба


В регистратуру вносится карта пациента (мед. регистратор)
- код пациента (окончательный)
- ФИО
- Дата рождения
- адрес проживания
  - номер участка

Далее идет к участковому врачу
- жалобы
- Краткая история
- состояние при осмотре
- диагностика
- рекомендации:
  - лечение по рецепту:
    - рецепт (содержит:
    - твой номер
    - данные медицинского регистратора
    - наркотики
  - направление к другому врачу (закрывает карточку и сохраняет в архив)
  - направление на обследование (закрывает карточку и сохраняет в архив)
  - без дополнительных рекомендаций (закрывает карту и сохраняет в архив)
- направление на подпись заведующему поликлиникой

управляющий делами
- Одобряет или отклоняет.\
  После одобрения отправьте рецепт в аптеку.

Аптека
- отметка о выдаче ---> отправка закрытого рецепта в архив

- # OutpatientService

In the registry, a patient card is entered (Med. registrar)
- patient code (final)
- full name
- Date of Birth
- residential address
  -plot number

Next goes to the local doctor
- complaints
- brief history
- condition on inspection
- diagnosis
- recommendations:
  - treatment with a prescription:
    - recipe (contains:
    - your number
    - data from the medical registrar
    - drugs
  - referral to another doctor (closes the card and saves to the archive)
  - referral for examination (closes the card and saves to the archive)
  - without additional recommendations (closes the map and saves to the archive)
- sending for signature to the head of the outpatient service

manager
- Approves or rejects.\
  When approved, send the prescription to the pharmacy

Pharmacy
- mark of issuance ---> sending a closed prescription to the archive
