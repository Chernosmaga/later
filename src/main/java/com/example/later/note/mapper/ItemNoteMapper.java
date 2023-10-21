package com.example.later.note.mapper;

import com.example.later.note.ItemNote;
import com.example.later.note.dto.ItemNoteDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemNoteMapper {

    public ItemNoteDto toItemNoteDto(ItemNote itemNote) {
        String date = DateTimeFormatter
                .ofPattern("yyyy.MM.dd hh:mm:ss")
                .withZone(ZoneOffset.UTC)
                .format(itemNote.getNoteDate());
        return ItemNoteDto.builder()
                .id(itemNote.getId())
                .itemId(itemNote.getItem().getId())
                .text(itemNote.getText())
                .dateOfNote(date)
                .build();
    }

    public ItemNote toItemNote(ItemNoteDto itemNoteDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(itemNoteDto.getDateOfNote(), formatter);
        return ItemNote.builder()
                .id(itemNoteDto.getId())
                .text(itemNoteDto.getText())
                .text(itemNoteDto.getText())
                .noteDate(dateTime).build();
    }
}
