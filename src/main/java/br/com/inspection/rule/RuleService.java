package br.com.inspection.rule;

import br.com.inspection.item.ItemService;
import br.com.inspection.item.ItemVO;
import br.com.inspection.server.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuleService extends AbstractService<Rule, RuleVO> {

    @Autowired
    private ItemService itemService;

    @Override
    public Class<RuleVO> getClazz() {
        return RuleVO.class;
    }

    @Override
    public Class<Rule> getEntityClazz() {
        return Rule.class;
    }

    public RuleItemsVO findItemsByRuleId(final UUID id) {
        final RuleVO rule = getById(id);
        return setItemsVOIntoRuleVO(rule);
    }

    private RuleItemsVO setItemsVOIntoRuleVO(final RuleVO ruleVO) {
        final List<ItemVO> listItems = itemService.getItemsByRuleId(ruleVO.getKey());
        final RuleItemsVO ruleItemsVO = new RuleItemsVO();
        ruleItemsVO.setKey(ruleVO.getKey());
        if (CollectionUtils.isNotEmpty(listItems)) {
            ruleItemsVO.setItems(listItems);
        }
        return ruleItemsVO;
    }
}
