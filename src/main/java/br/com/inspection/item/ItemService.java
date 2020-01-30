package br.com.inspection.item;

import br.com.inspection.server.AbstractService;
import br.com.inspection.server.adapter.DozerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService extends AbstractService<Item, ItemVO> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Class<ItemVO> getClazz() {
        return ItemVO.class;
    }

    @Override
    public Class<Item> getEntityClazz() {
        return Item.class;
    }

    public List<ItemVO> getItemsByRuleId(final UUID ruleId) {
        final List<Item> itemList = itemRepository.findByRuleId(ruleId);
        return DozerAdapter.parseListObjects(itemList, ItemVO.class);
    }
}
