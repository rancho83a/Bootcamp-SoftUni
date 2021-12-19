package bg.bootCamp.recrutmentTool.init;

import bg.bootCamp.recrutmentTool.model.entity.SkillEntity;
import bg.bootCamp.recrutmentTool.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RecrutmentToolApplicationInit implements CommandLineRunner {
    private final SkillRepository skillRepository;

    public RecrutmentToolApplicationInit(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if(skillRepository.count()==0){
            initSkills();
        }
    }

    private void initSkills(){

        initSkill("VueJS");
        initSkill("Java");
        initSkill("Angular");
        initSkill("C#");

    }

    private void initSkill(String name){
        SkillEntity skill = new SkillEntity().setName(name).setCandidatesCount(0);
        this.skillRepository.save(skill);
    }
}
