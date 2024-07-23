# 리팩토링 수행

## StartView 반복 코드 수정
forEach 사용

```java
// 데이터 구성 후 서비스 로직 실행
projects.forEach(controller::donationProjectInsert);
/*
 * controller.donationProjectInsert(schweitzerProject) ;
 * controller.donationProjectInsert(audreyHepbunPorject);
 * controller.donationProjectInsert(motherTeresaProject);
 */
```

## Controller - Insert 코드 수정
Optional 사용 예외 처리

```java
public void donationProjectInsert(TalentDonationProject project) {
		String projectName = project.getTalentDonationProjectName();
		projectName = "";
		Optional<String> opt = Optional.ofNullable(projectName);
		opt.ifPresentOrElse(
				n -> {
					if (n.isEmpty()) {
						FailView.failViewMessage("입력 부족, 재 확인 하세요~~");
						return;
					}
					try {

						service.donationProjectInsert(project);
						EndView.successMessage("새로운 프로젝트 등록 성공했습니다.");

					} catch (Exception e) {
						FailView.failViewMessage(e.getMessage()); // 실패인 경우 예외로 end user 서비스
						e.printStackTrace();
					}
				},
				() -> FailView.failViewMessage("입력 부족, 재 확인 하세요~~"));
	}
```

## Service - Update 수정
```java
public void beneficiaryProjectUpdate(String projectName, Beneficiary people) {
		donationProjectList.stream()
				.filter(p -> p != null && p.getTalentDonationProjectName().equals(projectName))
				.forEach(p -> p.setProjectBeneficiary(people));

	}
```
