package poem.boundary;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

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
			.byAnyPackage("..boundary.internal.command_handler..");
	
	@ArchTest
	public static final ArchRule insideDoesntAccessBoundary = noClasses().that()
			.resideInAPackage("..boundary.internal..").should().accessClassesThat()
			.resideInAPackage("..boundary");
	
	@ArchTest
	public static final ArchRule insideDoesntAccessDriverAdapters = noClasses().that()
			.resideInAPackage("..boundary.internal..").should().accessClassesThat()
			.resideInAPackage("..driver_adapter..");
	
	@ArchTest
	public static final ArchRule insideDoesntAccessDrivenAdapters = noClasses().that()
			.resideInAPackage("..boundary.internal..").should().accessClassesThat()
			.resideInAPackage("..driven_adapter..");
	
	@ArchTest
	public static final ArchRule useCaseModelIsOnlyAccessedByBoundaryClass = classes().that()
			.haveSimpleName("UseCaseModel").should().onlyBeAccessed().byClassesThat().haveSimpleName("Boundary");

}
