package com.gentech.menu.mapper;

import com.gentech.menu.dto.MenuDto;
import com.gentech.menu.entity.Menu;

public class MenuMapper {
		public static Menu MenuDtoToMenu(MenuDto dto)
		{
			Menu menu=new Menu(dto.getId(),
					dto.getName(),
					dto.getDescription(),
					dto.getCategory(),
					dto.getPrice(),
					dto.getCreatedAt(),
					dto.getUpdatedAt());
			return menu;
		}
		public static MenuDto MenuToMenuDto(Menu menu)
		{
			MenuDto menuDto=new MenuDto(menu.getId(),
					menu.getName(),
					menu.getDescription(),
					menu.getCategory(),
					menu.getPrice(),
					menu.getCreatedAt(),
					menu.getUpdatedAt());
			return menuDto;
		}
}
