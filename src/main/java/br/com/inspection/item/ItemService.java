package br.com.inspection.item;

import br.com.inspection.server.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractService<Item, ItemVO> {
    @Override
    public Class<ItemVO> getClazz() {
        return ItemVO.class;
    }

    @Override
    public Class<Item> getEntityClazz() {
        return Item.class;
    }
}
