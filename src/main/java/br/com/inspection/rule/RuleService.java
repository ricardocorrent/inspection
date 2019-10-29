package br.com.inspection.rule;

import br.com.inspection.item.Item;
import br.com.inspection.server.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RuleService extends AbstractService<Rule, RuleVO, RuleRequestVO> {

    @Override
    public Class<RuleVO> getClazz() {
        return RuleVO.class;
    }

    @Override
    public Class<Rule> getEntityClazz() {
        return Rule.class;
    }

    @Override
    public Class<RuleRequestVO> getEntityRequestClazz() {
        return RuleRequestVO.class;
    }

    @Override
    protected void doGenerateUpdateValues(final Rule rule) {
        if (CollectionUtils.isNotEmpty(rule.getItems())) {
            setParentIfItemHasChildren(rule.getItems());
        }
    }

    private void setParentIfItemHasChildren(final Set<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            final Item item = (Item) items.toArray()[i];
            if (CollectionUtils.isNotEmpty(item.getChildren())) {
                setParentIntoChildren(item.getChildren(), item);
                setParentIfChildrenHasChildren(item.getChildren());
            }
        }
    }

    private void setParentIfChildrenHasChildren(final Set<Item> children) {
        for (int i = 0; i < children.size(); i++) {
            final Item item = (Item) children.toArray()[i];
            if (CollectionUtils.isNotEmpty(item.getChildren())) {
                setParentIntoChildren(item.getChildren(), item);
                setParentIfChildrensChildrenHasChildren(item.getChildren());
            }
        }
    }

    private void setParentIfChildrensChildrenHasChildren(final Set<Item> children) {
        for (int i = 0; i < children.size(); i++) {
            final Item item = (Item) children.toArray()[i];
            if (CollectionUtils.isNotEmpty(item.getChildren())) {
                setParentIntoChildren(item.getChildren(), item);
            }
        }
    }

    private void setParentIntoChildren(final Set<Item> children, final Item parent) {
        children.forEach(item -> item.setParent(parent));
    }

}
