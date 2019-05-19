package poem.boundary;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "poem")
public class BoundaryIntegrityTest {
	@ArchTest
	public static final ArchRule commandHandlersAreOnlyAccessedByBoundaryClass = classes().that()
			.resideInAPackage("..boundary.internal.commandhandler..").should().onlyBeAccessed().byClassesThat()
			.haveSimpleName("Boundary");
	
	@ArchTest
	public static final ArchRule domainIsOnlyAccessedByCommandHandlers = classes().that()
			.resideInAPackage("..boundary.internal.domain..").should().onlyBeAccessed()
			.byAnyPackage("..boundary.internal.commandhandler..");
	
	@ArchTest
	public static final ArchRule useCaseModelIsOnlyAccessedByBoundaryClass = classes().that()
			.haveSimpleName("UseCaseModel").should().onlyBeAccessed().byClassesThat().haveSimpleName("Boundary");

	@ArchTest
	public static final ArchRule commandHandlersOnlyAccessCommandsAndDrivenPortsAndDomainAndStandardJava = classes()
			.that().resideInAPackage("..boundary.internal.commandhandler..").should().onlyAccessClassesThat()
			.resideInAnyPackage("..command..", "..boundary.port..", "..boundary.internal.domain..", "java.lang..",
					"java.util..");
}
