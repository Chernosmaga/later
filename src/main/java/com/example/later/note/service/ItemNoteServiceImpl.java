package com.example.later.note.service;

import com.example.later.exception.NotFoundException;
import com.example.later.note.ItemNote;
import com.example.later.note.dto.ItemNoteDto;
import com.example.later.note.mapper.ItemNoteMapper;
import com.example.later.note.repository.ItemNoteRepository;
import com.example.later.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemNoteServiceImpl implements  ItemNoteService {
    private final ItemNoteRepository itemNoteRepository;
    private final UserRepository userRepository;
    private final ItemNoteMapper mapper;

    @Override
    public ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        ItemNote item = mapper.toItemNote(itemNoteDto);
        return mapper.toItemNoteDto(itemNoteRepository.save(item));
    }

    @Override
    public List<ItemNoteDto> searchNotesByUrl(String url, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        List<ItemNote> list = itemNoteRepository.findAllByItemUrlContainingAndItemUserId(url, userId);
        return list.stream().map(mapper::toItemNoteDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemNoteDto> searchNotesByTag(long userId, String tag) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        List<ItemNote> list = itemNoteRepository.findByTag(userId, tag);
        return list.stream().map(mapper::toItemNoteDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        PageRequest page = PageRequest.of(from > 0 ? from / size : 0, size);
        return itemNoteRepository.findAllByItemUserId(userId, page).map(mapper::toItemNoteDto).getContent();
    }
}
