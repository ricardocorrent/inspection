package br.com.inspection.rule;

import br.com.inspection.item.Item;
import br.com.inspection.server.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RuleService extends AbstractService<Rule, RuleVO> {

    @Override
    public Class<RuleVO> getClazz() {
        return RuleVO.class;
    }

    @Override
    public Class<Rule> getEntityClazz() {
        return Rule.class;
    }

    @Override
    protected void doGenerateUpdateValues(final Rule rule) {
        if (CollectionUtils.isNotEmpty(rule.getItems())) {
            final Set<Item> items = rule.getItems();

            items.forEach(item -> {
                if (CollectionUtils.isNotEmpty(item.getChildren())) {
                    setParentIntoChildren(item.getChildren(), item);
                    final Set<Item> children = item.getChildren();
                    children.forEach(item2 -> setParentIntoChildren(item2.getChildren(), item2));
                }
            });

        }
    }

    private void setParentIntoChildren(final Set<Item> items, final Item parent) {
        items.forEach(item -> item.setParent(parent));
    }

}
