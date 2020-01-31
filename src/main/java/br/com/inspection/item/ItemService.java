package br.com.inspection.item;

import br.com.inspection.item.vo.ItemsFromRuleIdResponseVO;
import br.com.inspection.item.vo.RuleToItemsRsponseVO;
import br.com.inspection.rule.Rule;
import br.com.inspection.rule.RuleRepository;
import br.com.inspection.server.AbstractService;
import br.com.inspection.server.adapter.DozerAdapter;
import br.com.inspection.server.validation.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService extends AbstractService<Item, ItemVO> {

    @Autowired
    private RuleRepository ruleRepository;

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

    public RuleToItemsRsponseVO getItemsByRuleId(final UUID ruleId) {
        final Rule rule = ruleRepository.findById(ruleId).orElseThrow(RegisterNotFoundException::new);

        final List<Item> itemList = itemRepository.findByRuleId(rule.getId());
        return createResponseToSendItemsFromRuleId(itemList, rule);
    }

    private RuleToItemsRsponseVO createResponseToSendItemsFromRuleId(final List<Item> itemList, final Rule rule) {
        final RuleToItemsRsponseVO response = new RuleToItemsRsponseVO();
        response.setKey(rule.getId());
        response.setTitle(rule.getTitle());
        response.setDescription(rule.getDescription());
        response.setItems(DozerAdapter.parseListObjects(itemList, ItemsFromRuleIdResponseVO.class));
        return response;
    }
}
