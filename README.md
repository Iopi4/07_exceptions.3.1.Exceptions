Домашнее задание к занятию «2.3. ООП: композиция, наследование и интерфейсы» - Выполнено.
Задача №1. Nullable:
 - Добавлены nullable поля в класс Post.(text, replyOwnerId, replyPostId, comments, likes)
Задача №2. Attachments:
 - Attachment — базовый тип с полем type (взят sealed interface)
 - Наследники: PhotoAttachment, VideoAttachment, AudioAttachment, DocAttachment, LinkAttachment
 - Для «полезной нагрузки» — отдельные data-классы: Photo, Video, Audio, Doc, Link.
 - В Post добавлено attachments: List<Attachment> = emptyList()
