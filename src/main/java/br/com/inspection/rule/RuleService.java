package br.com.inspection.rule;

import br.com.inspection.server.AbstractService;
import org.springframework.stereotype.Service;

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

}
