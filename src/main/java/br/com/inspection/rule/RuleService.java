package br.com.inspection.rule;

import br.com.inspection.item.Item;
import br.com.inspection.server.AbstractService;
import br.com.inspection.server.adapter.DozerAdapter;
import br.com.inspection.server.validation.exception.RegisterNotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;

@Service
public class RuleService extends AbstractService<Rule, RuleVO> {

    private RuleRepository ruleRepository;

    @Inject
    public RuleService(final RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Class<RuleVO> getClazz() {
        return RuleVO.class;
    }

    @Override
    public Class<Rule> getEntityClazz() {
        return Rule.class;
    }

    public RuleVO update(final RuleRequestVO ruleRequestVO) {
        final Rule ruleFromDb = ruleRepository
                .findById(ruleRequestVO.getKey()).orElseThrow(RegisterNotFoundException::new);

        final Rule rule = DozerAdapter.parseObject(ruleRequestVO, Rule.class);
        this.doGenerateUpdateValues(rule);
        if (ruleFromDb != null) {
            return convertEntityToEntityVO(ruleRepository.save(rule));
        } else {
            throw new RegisterNotFoundException();
        }
    }

    private void doGenerateUpdateValues(final Rule rule) {
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
