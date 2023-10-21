package com.example.later.item.service;

import com.example.later.item.dto.AddItemRequest;
import com.example.later.item.dto.GetItemRequest;
import com.example.later.item.dto.ItemDto;
import com.example.later.item.dto.ModifyItemRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ItemService {
    List<ItemDto> getItems(Long userId);

    @Transactional
    ItemDto addNewItem(Long userId, AddItemRequest request);

    @Transactional
    void deleteItem(Long userId, Long itemId);


    List<ItemDto> getItems(GetItemRequest req);

    ItemDto changeItem(Long userId, ModifyItemRequest request);

    @Transactional(readOnly = true)
    List<ItemDto> getUserItems(String lastName);
}

