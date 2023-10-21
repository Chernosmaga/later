package com.example.later.item.controller;

import com.example.later.item.dto.AddItemRequest;
import com.example.later.item.dto.GetItemRequest;
import com.example.later.item.dto.ItemDto;
import com.example.later.item.dto.ModifyItemRequest;
import com.example.later.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getItems(@RequestHeader("X-Later-User-Id") Long userId,
                                  @RequestParam(defaultValue = "unread") String state,
                                  @RequestParam(defaultValue = "all") String contentType,
                                  @RequestParam(defaultValue = "newest") String sort,
                                  @RequestParam(defaultValue = "10") Integer limit,
                                  @RequestParam(required = false) List<String> tags) {
        return itemService.getItems(GetItemRequest.of(userId, state, contentType, sort, limit, tags));
    }

    @GetMapping("/{userId}")
    public List<ItemDto> get(@PathVariable String userId) {
        return itemService.getItems(Long.parseLong(userId));
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
                       @RequestBody AddItemRequest request) {
        return itemService.addNewItem(userId, request);
    }

    @DeleteMapping("/{itemId}")
    public void delete(@RequestHeader("X-Later-User-Id") long userId,
                           @PathVariable long itemId) {
        itemService.deleteItem(userId, itemId);
    }

    @PatchMapping
    public ItemDto update(@RequestHeader("X-Later-User-Id") long userId,
                              @RequestBody ModifyItemRequest request) {
        return itemService.changeItem(userId, request);
    }
}